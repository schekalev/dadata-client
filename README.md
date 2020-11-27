# DaData client

Fork of Dadata Suggestions API client based on OkHttp. 

## Overview

Dadata API client based on OkHttpClient and can be used in Java SE without Spring framework.

## Add a dependency

Gradle:
	
	Coming soon
	
Maven:
	
	Coming soon

## Usage

```
        
    String token = ...;
    DadataClient client = new DadataClientBuilder()
                                   .token(token).build();
    ...
    String query=...
    SuggestionAddress suggest = client.suggestAddress(AddressRequestBuilder.create(query).build());
  
```

Request builders allow filter and rank suggestions. More examples are available in the `test` module 
