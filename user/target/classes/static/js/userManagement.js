document.addEventListener("DOMContentLoaded", fetchUsers);

function fetchUsers() {
        	 let userTable = document.getElementById('userTable');
        	let existingTbody = document.querySelector("#userTable tbody");
            if (existingTbody) {
                existingTbody.remove(); // Remove old tbody if it exists
            }
            
            let tbody = document.createElement('tbody');
            tbody.innerHTML = "<tr><td colspan='4'>Loading...</td></tr>";
            userTable.appendChild(tbody);

            
            fetch('http://localhost:808/user/jpa/users')
                .then(response => response.json())
                .then(users => {
                	tbody.innerHTML = ''; // Clear loading message
                    userTable.innerHTML = '<tr><th>ID</th><th>Name</th></tr>';
                    users.forEach(user => {
                    	 let row = document.createElement('tr');
                    	 row.innerHTML = `<td>${user.id}</td><td>${user.name}</td>`;
                    	 tbody.appendChild(row);
                    });
                    userTable.appendChild(tbody);
                });
        }
        function success(){
        	window.location.href = "/user/success.html";
        }

        function addUser() {
            let name = document.getElementById('name').value;
            let id = document.getElementById('id').value;
            
            fetch('http://localhost:8080/user/jpa/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: name,
                    dateTime : new Date().toJSON().slice(0, 10),
                    id: parseInt(id)  // Convert to number
                })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Failed to add user");
                }
                fetchUsers();
                //window.location.href = "http://localhost:8080/success.html";
                return response.json();
            })
           .catch(error => console.error("Error:", error))
        }