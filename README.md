# Json Flattener
Simple command line program to flatten and print json. Input json must be supplied via a file.

## Sample Input/Ouput

* Input
```json
{
    "a": 1,
    "b": true,
    "c":
    {
        "d": 3,
        "e": "test",
        "f":
        {
            "g": null,
            "h":
            {
                "i": 100000000,
                "j": 1.5
            }
        }
    }
}


```

* Expected output (pretty-printed in console)
```json
{
  "a" : 1,
  "b" : true,
  "c.d" : 3,
  "c.f.h.j" : 1.5,
  "c.e" : "test",
  "c.f.h.i" : 100000000,
  "c.f.g" : null
}
```

## Pre-req
* You will need to install JDK (9+).

# Usage
## Build Project:
* Navigate to project dir in commandline.
* Run `./gradlew build`

## Running the program
* Navigate to project's `build/libs` dir where you will find the `json-flattener-1.0.jar` file.
* Copy the json file you want to flatten in this `build/libs` dir.
* Run following command: `java -jar json-flattener-1.0.jar < <json-file>` OR `cat <json-file> | java -jar json-flattener-1.0.jar`.
* For e.g. if you had an input file name named - `inputJson.json`, then you can flatten it using any of the following commands: `java -jar json-flattener-1.0.jar < inputJson.json` OR `cat inputJson.json | java -jar json-flattener-1.0.jar`.



