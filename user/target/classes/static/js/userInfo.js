document.addEventListener("DOMContentLoaded", async function() {
	const token = localStorage.getItem("token");
	var params = new URLSearchParams(window.location.search);
	const email = params.get("name");

	if (!token) {
		alert("You are not logged in! Redirecting to login.");
		window.location.href = "http://localhost:8080/user/login";
		return;
	}
	if (!email) {
		document.getElementById("userDetails").innerHTML = "<p>Email is missing in URL</p>";
		return;
	}

	fetchUserInfo(email, token);
});

async function fetchUserInfo(email, token) {
	const userDetailsDiv = document.getElementById("userDetails");

	try {
		const response = await fetch(`/user/users/email/${encodeURIComponent(email)}`, {
			method: "GET",
			headers: {
				"Authorization": `Bearer ${token}`,
				"Content-Type": "application/json"
			}
		});
		const user = await response.json();
		
		if (response.ok) {
			userDetailsDiv.innerHTML = `
			<p><strong>Full Name:</strong> ${user.fullName}</p>
			<p><strong>Email:</strong> ${user.email}</p>
			<p><strong>Created At:</strong> ${user.createdAt}</p>
			`;
		} else {
			if(response.status === 404 ){
				userDetailsDiv.innerHTML = `<p>No user found with email: ${email}`;
			} else {
				userDetailsDiv.innerHTML = `<p>Error fetching user info: ${response.statusText}</p>`;
			}
		}
	}
	catch(error){
		userDetailsDiv.innerHTML = `<p>Error fetching user info: ${error.message}</p>`;
	}
}