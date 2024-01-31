//"OpenAI. (2023). JavaScript Coding Examples and Explanations. ChatGPT Session. General Reference"



document.getElementById('login-btn').addEventListener('click', function(event) {
    event.preventDefault();

    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    var url = "http://localhost:8081/A4/LoginServlet" +
    "?username=" + encodeURIComponent(username) +
    "&password=" + encodeURIComponent(password);

    fetch(url)
    .then(response => response.json())
    .then(data => {
        if (data.status === 'success') {
            window.location.href = '/A4/home.html'; 
        } else {
            alert(data.message); 
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
});




document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('register-btn').addEventListener('click', function(event) {
        event.preventDefault(); 

        var email = document.getElementById('email').value;
        var username = document.getElementById('newUsername').value;
        var password = document.getElementById('newPassword').value;
        var confirmPassword = document.getElementById('confirmPassword').value;
        var termsChecked = document.getElementById('terms').checked;

       
       

        
        var url = "http://localhost:8081/A4/SignupServlet" +
    		"?email=" + encodeURIComponent(email) + 
    		"&username=" + encodeURIComponent(username) +
    		"&password=" + encodeURIComponent(password) +
    		"&confirmPassword=" + encodeURIComponent(confirmPassword) +
    		"&termsChecked=" + termsChecked;

        fetch(url)
            .then(response => response.json())
            .then(data => {
                if (data.status === 'success') {
                    console.log("Sucess");
                   
                    window.location.href = '/A4/home.html'; 
                } else {
                    console.log("Fail");
                    
                    alert(data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });

    
   
});
