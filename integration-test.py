import requests

login = requests.post("http://localhost:8080/loginasd", 
    json={"username": "test@example.com", "password": "password"}
)

# print(login.headers["Set-Cookie"])

print(login.json())

# register = requests.post("http://localhost:8080/users", 
#     json={"email": "test@example.com", "password": "password"}
# )

# print(register.json())