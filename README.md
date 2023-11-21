# Solution 1
using Haversine formula find the scope min longitude - max longitude, min latitude - max latitude

advantage: without any package or database

disadvantage: poor performance





# Solution 2
# Introduction
Using redis for caching and saving location 
Geo can helps calculate and sort user nearby.

Even with billions data, only first query will query the database, after first query save cache into redis will response ASAP

should also do data persistence in case redis restart

advantage: goo performance, fast response 

![a608b0fb443ae08822f5c85bddc636a7.png](https://i.mji.rip/2023/11/21/a608b0fb443ae08822f5c85bddc636a7.png)



