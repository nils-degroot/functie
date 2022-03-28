# Package nl.peeko.functie.outcome

A [Outcome] represents on of two cases. A successful method, or a failed one.
This is represented with the following types:

## [Ok]

The method went [Ok]. The response value for the method can be found at 
[Ok. value].

## [Failure]

Something went wrong with the method. Instead of an exception occurring, the 
corresponding throwable can be found in the failure ([Failure.throwable]).