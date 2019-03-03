# Sainsbury web page reader

## Content

- **[Overview](#overview)**
- **[Usage](#usage)**
  - **[Requirements](#requirements)**
  - **[Installation](#installation)**
  - **[Download and Run](#download-and-run)**
  - **[Result](#result)**
  
## Overview
A Java console application that scrapes a portion of the Sainsbury’s Groceries website.

## Usage

## Requirements

- Java8 or higher
- Maven 3

## Download and Run
To download the application run the following command:
```bash
git clone https://github.com/eliana1507/sainsbury-page-reader.git
```

To execute the application run the following command

```bash
 mvn -q clean compile exec:java -Dexec.mainClass="eliana.cappello.SainsburyWebPageReader" -Dexec.args="--webpage urlToRead"
```

## Result
The result this application returns is going to have the following format:

```json
{
  "result" : [ {
    "title" : "Title of the product",
    "kcal_per_100g" : 33,
    "unit_price" : 1.75,
    "description" : "Product's description"
  } ],
  "total" : {
    "gross" : 32.25,
    "vat" : 6.45
  }
}
``` 