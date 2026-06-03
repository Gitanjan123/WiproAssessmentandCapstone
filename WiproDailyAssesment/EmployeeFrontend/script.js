const BASE_URL = "http://localhost:8081";

// Signup
function signup() {
    const username = document
                     .getElementById("signupUsername").value;
    const password = document
                     .getElementById("signupPassword").value;

    fetch(`${BASE_URL}/signup`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: username,   // ← matches Employee.java
            password: password
        })
    })
    .then(res => res.text())
    .then(data => {
        document.getElementById("result").innerText = data;
    });
}

// Login
function login() {
    const username = document
                     .getElementById("loginUsername").value;
    const password = document
                     .getElementById("loginPassword").value;

    fetch(`${BASE_URL}/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: username,   // ← matches Employee.java
            password: password
        })
    })
    .then(res => res.text())
    .then(token => {
        localStorage.setItem("jwtToken", token);
        document.getElementById("result").innerText =
            "Login Success! Token Saved ✅";
    });
}

// Profile
function getProfile() {
    const token = localStorage.getItem("jwtToken");

    fetch(`${BASE_URL}/profile`, {   // ← fixed /all to /profile
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(res => res.text())
    .then(data => {
        document.getElementById("result").innerText = data;
    });
}

// load these data into a list (cart like styling)
//load these data into a  grid