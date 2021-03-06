# dipPI

![Styracosaurus](images/IMG_4496-2.JPG)

_Styracosaurus albertensis_
Photo Copyright © 2017 seb231
Drawing Copyright © 2017 [James Robbins](http://www.jr-illustration.co.uk/)

## About

A Clojure library for calling the Natural History Museum's Data Portal API:

http://data.nhm.ac.uk/about/download

DOI: 10.5519

## Usage
First download or clone the repository.

### From the command line
From the `dipPI` root directory call the program like this:
```
./dippi.sh -d "specimens" -q "Archaeopteryx" -f "catalogNumber" -n "On"
```
```
-d database
-q query
-f field
-n record count
```
Options `-f` and `-n` are optional.

### In the REPL
Start a REPL and run either `query-nhm-api` or `filter-nhm-api` with a database name and the following:

A "**Query**" search expects a name of a [database](http://data.nhm.ac.uk/dataset?author=Natural+History+Museum) and a query string i.e "Archaeopteryx".

A "**Filter**" search expects a name of a [database](http://data.nhm.ac.uk/dataset?author=Natural+History+Museum) and a filter field and search string i.e "catalogNumber" and "PV P 51007"

#### Databases

Available databases include: `collection-specimens`, `artefacts`, `former-bp`, `index-lot` and `interactions-bank`.

More information about the databases can be found here: http://data.nhm.ac.uk/dataset

## TODO

Interrogate some collection results to see what further "get" functions would be beneficial.

## License

Copyright © 2017 seb231
