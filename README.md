# bhl-second-life-backend
### `base URL: localhost:8080`
## `advertisement` endpoints
#### `/advertisement`
* `GET` - get all advertisements,
* `POST` - add new with advertisement in body,
#### `/advertisement/{id}`
* `GET` - get single advertisement with given id,
* `DELETE` - delete advertisement with given id,
#### `/advertisement/user/{username}
* `GET` - with request paramas "longitude" and "latitude" - returns list of advertisements to display for given user

### `advertisement JSON example`
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
  
