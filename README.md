Rest Api call for SignUp User
http://localhost:8080/api/auth/signup

Sample Post Request Call:
{
    "username": "Abcs",
    "password": "Abcd1234@",
    "firstName": "Abc",
    "lastName": "Def",
    "email": "Abc90@example.com",
    "mobile": "8888888888"
}

Rest Api call for User Login In:
http://localhost:8080/api/profile/login

Sample Post Request Call:
{
  "username": "Abcs",
  "password": "NewPassword1!"
}
In authorization You have to select Basic Auth and provide Username and password
And In Headers You have to write Key: Origin and Value: 27ab17d1-215f-49e5-9ca4-afd48810c149

Rest Api Call for Update User:
http://localhost:8080/api/profile/update

Sample Post Request Call:
{
  "firstName": "Abcdefg",
  "lastName": "Gupta",
  "mobile": "8888888888",
  "password": "NewPassword1!"
}
In authorization You have to select Basic Auth and provide Username and password
