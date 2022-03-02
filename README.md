# Deskoin market

This repository consists in a technical interview.

**Please read the instructions below.**

## Prerequisites

Install the following prerequisites to build and run the project.

* Java 11
* Maven
* Git

## Installation

Use the package manager Maven to install the modules.

```bash
mvn clean install
```

## Usage

### Run an application locally

Use the package manager Maven to run a Spring Boot application. This command must be
executed in the folder of the application you want to run.

```bash
cd app
mvn spring-boot:run
```

This application is running on http://localhost:9091

## Project description

The project consists of the global BTC / EUR market (in this example composed of Kraken and Binance exchanges).

Each market has a name and contains a list of values (price, volume).
The price is expressed in EUR.
Volume is the volume of BTC available for the given price.

_Example: {10,1} means that there is 1 BTC available for the price of 10 EUR/u._

Market values are constantly changing (randomly in this project).

To inspire you, we have already created 2 endpoints:
1. The list of all markets
2. The market by name

## General purpose

We expect you to write production code.
By production code we mean that your code should be ready to be pushed into production.
So write good code, test your code, and add documentation if needed.
Also use a correct Git flow/branching.

Good luck & Enjoy!

## Instructions

This section provides instructions for completing the test, read them carefully.
You can use the following endpoints to view markets:
1. GET /markets
2. GET /markets/{name}

Try to always return a JSON response body.

### Exercise 1

Create an endpoint that returns the lowest price in the global market.

Example:
```
object={price,volume}

Kraken=[
  {10,1}, <--- you must return this price
  {42,2},
  {50,10},
  {30,4},
  {90,1}
]
Binance=[
  {90,8},
  {40,8},
  {100,10},
  {20,10},
  {55,1}
]

result=10
```

Response body:
```
{
  "price": 10.00
}
```

### Exercise 2

Create an endpoint that returns the global market in ascending price order.
If 2 prices are equal, the order is not important (see in the example below price = 90).

Example:
```
object={price,volume}

Kraken=[
  {10,1},
  {42,2},
  {50,10},
  {30,4},
  {90,1}
]
Binance=[
  {90,8},
  {40,8},
  {100,10},
  {20,10},
  {55,1}
]

result=[
  {10,1},
  {20,10},
  {30,4},
  {40,8},
  {42,2},
  {50,10},
  {55,1},
  {90,1}
  {90,8},
  {100,10},
]
```

Response body:
```
[
  {
    "price": 10.00,
    "volume": 1.00
  },
  {
    "price": 20.00,
    "volume": 10.00
  },
  {
    "price": 30.00,
    "volume": 4.00
  },
  {
    "price": 40.00,
    "volume": 8.00
  },
  {
    "price": 42.00,
    "volume": 2.00
  },
  {
    "price": 50.00,
    "volume": 10.00
  },
  {
    "price": 55.00,
    "volume": 1.00
  },
  {
    "price": 90.00,
    "volume": 1.00
  },
  {
    "price": 90.00,
    "volume": 8.00
  },
  {
    "price": 100.00,
    "volume": 10.00
  }
]
```

### Exercise 3

Create an endpoint that returns the volume (BTC) that can be obtained for a given amount (EUR).

_Tip: reuse the exercise 2_

```
object={price,volume}

Kraken=[
  {10,1},
  {42,2},
  {50,10},
  {30,4},
  {90,1}
]
Binance=[
  {90,8},
  {40,8},
  {100,10},
  {20,10},
  {55,1}
]

input: amount=10 (EUR)
output: result=1 (BTC)
// 1 BTC for 10 EUR/u

input: amount=30 (EUR)
output: result=2 (BTC)
// 1 BTC for 10 EUR/u
// 1 BTC for 20 EUR/u

input: amount=50 (EUR)
output: result=3 (BTC)
// 1 BTC for 10 EUR/u
// 2 BTC for 20 EUR/u

input: amount=60 (EUR)
output: result=3.5 (BTC)
// 1 BTC for 10 EUR/u
// 2.5 BTC for 20 EUR/u

```

Response body:
```
{
  "amount": 10.00,
  "volume": 1.00
}
```