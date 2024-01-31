let map;
let marker;
//"OpenAI. (2023). JavaScript Coding Examples and Explanations. ChatGPT Session." General Reference

console.log("Loaded");

function initMap() {
  const defaultLocation = { lat: -25.363, lng: 131.044 };
  map = new google.maps.Map(document.getElementById("map"), {
    zoom: 4,
    center: defaultLocation,
  });

  
  map.addListener("click", function(e) {
    placeMarkerAndPanTo(e.latLng, map);
  });
}

function placeMarkerAndPanTo(latLng, map) {
  
  if (marker) {
    marker.setMap(null);
  }

  
  marker = new google.maps.Marker({
    position: latLng,
    map: map
  });

  map.panTo(latLng);

  
  document.getElementById('latitude').value = latLng.lat();
  document.getElementById('longitude').value = latLng.lng();

  
  closeMap();
}

function showMap() {
  document.getElementById('mapPopup').style.display = 'block';
  google.maps.event.trigger(map, 'resize'); 
}

function closeMap() {
  document.getElementById('mapPopup').style.display = 'none';
}

window.initMap = initMap;
window.onload = initMap;



document.getElementById('searchbtn').addEventListener('click', function(event) {
    event.preventDefault();

    const restaurantName = document.querySelector('input[name="restaurantName"]').value;
    if (!restaurantName) {
        alert('Please enter a restaurant name.');
        return; 
    }
    const latitude = document.querySelector('input[name="latitude"]').value;
    const longitude = document.querySelector('input[name="longitude"]').value;
    const searchOptionInput = document.querySelector('input[name="searchOption"]:checked');

    
    if (searchOptionInput) {
        const searchOption = searchOptionInput.value;
        fetchSearchResults(restaurantName, latitude, longitude, searchOption);
    } else {
        alert('Please select a search option.');
    }
});

function fetchSearchResults(name, latitude, longitude, searchOption) {
    let url = new URL('http://localhost:8081/A4/SearchServlet');
    url.searchParams.append('action', 'search');

    if (name) {
        url.searchParams.append('restaurantName', name);
    }
    if (latitude && longitude) {
        url.searchParams.append('latitude', latitude);
        url.searchParams.append('longitude', longitude);
    }
    url.searchParams.append('searchOption', searchOption);
   

   
    fetch(url)
        .then(response => response.json())
        .then(data => {
            displaySearchResults(data);
            document.querySelector('.image-placeholder').style.display = 'none'; // Hide the image
        })
        .catch(error => {
            console.error('Error:', error);
        });
}



function displaySearchResults(results) {
    const resultsContainer = document.getElementById('searchResults');
    resultsContainer.innerHTML = ''; 

    results.businesses.forEach(business => {
        const resultDiv = document.createElement('div');
        resultDiv.className = 'result-item';
        resultDiv.innerHTML = `
            <div class="restaurant-image" onclick="showRestaurantDetails('${business.id}')">
                <img src="${business.image_url}" alt="${business.name}" class="result-image">
            </div>
            <div class="restaurant-info">
                <h3 class="restaurant-name">${business.name}</h3>
                <p class="restaurant-address">${business.location.address1}, ${business.location.city}</p>
                <a href="${business.url}" target="_blank" class="yelp-link">View on Yelp</a>
            </div>
        `;
        resultsContainer.appendChild(resultDiv);
    });
}


function showRestaurantDetails(businessId) {
    document.getElementById('searchResults').style.display = 'none';

    history.pushState({ page: "details" }, null, "");

    let detailsUrl = new URL('http://localhost:8081/A4/SearchServlet');
    detailsUrl.searchParams.append('action', 'details');
    detailsUrl.searchParams.append('businessId', businessId);

    fetch(detailsUrl)
        .then(response => response.json())
        .then(restaurantData => {
            const detailsContainer = document.getElementById('restaurantDetails');
            detailsContainer.innerHTML = `
                <div class="restaurant-details">
                    <div class="restaurant-details-content">
                        <a href="${restaurantData.url}" target="_blank" class="restaurant-image-link">
                            <img src="${restaurantData.image_url}" alt="${restaurantData.name}" class="detail-image">
                        </a>
                        <div class="restaurant-info">
                            <h2 class="restaurant-name">${restaurantData.name}</h2>
                            <p class="restaurant-address">Address: ${restaurantData.location.address1}, ${restaurantData.location.city}, ${restaurantData.location.state} ${restaurantData.location.zip_code}</p>
                            <p class="restaurant-phone">Phone No.: ${restaurantData.phone}</p>
                            <p class="restaurant-cuisine">Cuisine: ${restaurantData.categories.map(category => category.title).join(', ')}</p>
                            <p class="restaurant-price">Price: ${restaurantData.price}</p>
                            <div class="restaurant-rating">Rating: ${'★'.repeat(Math.round(restaurantData.rating))}</div>
                            <button class="button add-to-favorites">+ Add to Favorites</button>
                            <button class="button add-reservation" onclick="toggleReservationForm()">+ Add Reservation</button>
                        </div>
                    </div>
                    <div class="reservation-form">
                        <div class="date-time-container">
                            <input type="date" id="reservation-date" name="reservation-date">
                            <input type="time" id="reservation-time" name="reservation-time">
                        </div>
                        <textarea class="reservation-notes" placeholder="Reservation notes..."></textarea>
                        <button class="button submit-reservation">Submit Reservation</button>
                    </div>
                </div>
            `;

            const addToFavoritesBtn = document.querySelector('.add-to-favorites');
            addToFavoritesBtn.addEventListener('click', function() {
                if (addToFavoritesBtn.textContent.includes('Add to Favorites')) {
                    addToFavoritesBtn.textContent = 'Remove from Favorites';
                } else {
                    addToFavoritesBtn.textContent = 'Add to Favorites';
                }
            });

            detailsContainer.style.display = 'block'; 
        })
        .catch(error => console.error('Error fetching restaurant details:', error));
}


function toggleReservationForm() {
    var reservationForm = document.querySelector('.reservation-form');
    reservationForm.classList.toggle('visible');
}

window.onpopstate = function(event) {
    if (event.state && event.state.page === "details") {
        document.getElementById('restaurantDetails').style.display = 'none';
        document.getElementById('searchResults').style.display = 'block';
    }
};