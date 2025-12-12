import requests

login = requests.post("http://localhost:8080/loginasd", 
    json={"username": "a@a.com", "password": "pass"}
)

# print(login.headers["Set-Cookie"])
print(login.status_code)
print(login.json())

# register = requests.post("http://localhost:8080/users", 
#     json={"email": "test@example.com", "password": "password"}
# )

# print(register.json())