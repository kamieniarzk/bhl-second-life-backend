![](https://res.cloudinary.com/ddd3ldsj2/image/upload/v1604862587/Screenshot_3_azlxm6.jpg)<br><br>
## Overview
SecondLife is a prototype of an application used for trading items with other users using matchmaking. The prototype was made during BEST Hacking League Hackathon 2020. The problem given by the organizers was to make an application that would help people save time, money and ENVIRONMENT.<br><br>
![](https://res.cloudinary.com/ddd3ldsj2/image/upload/v1604862587/Screenshot_1_b0gsyf.jpg)
![](https://res.cloudinary.com/ddd3ldsj2/image/upload/v1604862587/Screenshot_2_elhspi.jpg)
![](https://res.cloudinary.com/ddd3ldsj2/image/upload/v1604863269/Screenshot_5_gqwyyf.jpg)<br><br>
## Architecture
The prototype consists of backend server made in Java with Spring framework (this repository), responsible for processing and transferring the data between the PostgreSQL database deployed on Heroku and UI made in Flutter. Additionally there's one more backend server made in Python, deployed on Heroku responsible for the chat between users (for the sake of simplicity and due to time limitations it's made using a Rest API). In this protorype version, the user authentication is mocked to only checking the username.
Other repositories are:
* frontend: [https://github.com/radziminski/bhl-second-life-frontend](https://github.com/radziminski/bhl-second-life-frontend),
* database: [https://github.com/wiktorlazarski/BHL5_db_queries](https://github.com/wiktorlazarski/BHL5_db_queries),
* chat server: [https://github.com/wiktorlazarski/BHL5_db_messenger](https://github.com/wiktorlazarski/BHL5_db_messenger)<br><br>
![](https://res.cloudinary.com/ddd3ldsj2/image/upload/v1604862587/Screenshot_4_xw016n.jpg)<br><br>
# bhl-second-life-backend
### `base URL: localhost:8080`
## `advertisement` endpoints
#### `/advertisement`
* `GET` - get all advertisements,
* `POST` - add new with advertisement in body,
#### `/advertisement/{id}`
* `GET` - get single advertisement with given id,
* `DELETE` - delete advertisement with given id,
#### `/advertisement/user/{username}`
* `GET` - with request params "longitude" and "latitude" - returns list of advertisements to display for given user
#### `/advertisement/matches/{username}`
* `GET` - with {username} in path, returns the list of matched advertisements for given user (match means this user likes some advertisement whose owner also likes some of this user's advertisements)
#### `/advertisement/user/{username}`
* `GET` - with {username} in path, returns the list of advertisements to show for given user. Because it's only a prototype it returns all the advertisements within the area range.
#### `/advertisement/like/{id}`
* `PATCH` - with given {id} which is the ID of an advertisement and {username} as a request param, it adds given advertisement to the list of liked advertisements for given user,
### `response JSON example`
```
{
        "id": 10,
        "title": "Cursed",
        "imageUrls": [
            "https://images.pexels.com/photos/4318198/pexels-photo-4318198.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
        ],
        "description": "Color vision study",
        "latitude": -7.779809,
        "longitude": 110.3562,
        "cretedDate": "2020-03-24T23:00:00.000+00:00",
        "owner": "Peterus",
        "itemCategories": [
            3,
            5
        ],
        "priceCategory": 3
}
```   
### `request JSON example`    
```
{
        "title": "Pope's Toilet, The (El bano del Papa)",
        "imageUrls": [
                "http://dummyimage.com/195x142.jpg/dddddd/000000",
                "http://dummyimage.com/223x233.png/5fa2dd/ffffff",
                "http://dummyimage.com/178x107.bmp/5fa2dd/ffffff",
                "http://dummyimage.com/125x212.bmp/dddddd/000000"
        ],
        (optional) "description": "Endovascular replacement of unspecified heart valve",
        "latitude": 4.740619,
        "longitude": 7.0848064,
        "ownerId": 1,
        "itemCategories": [
                1
        ],
        "priceCategory": 2
},
```
## `user` endpoints
#### `/user`
* `GET` - get all users,
* `POST` - add new with user in body,
#### `/user/{username}`
* `GET` - get single user with given name,
* `DELETE` - delete user with given username,
### `user JSON example (same for request and response)`
```
{
        "userName": "Wes",
        "userLastname": "Buzine",
        "radius": 1.942,
        "likedAdvertisements": [
            2,
            5,
            11
        ]
}
```


  
