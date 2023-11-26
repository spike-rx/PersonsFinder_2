# Solution 1
using Haversine formula find the scope min longitude - max longitude, min latitude - max latitude

advantage: without any package or database

disadvantage: poor performance





# Solution 2
# Introduction
Using Redis for caching and saving location
Geo can help calculate and sort users nearby.

Even with billions of data, only the first query will query the database, after the first query saves the cache Redis will respond ASAP
Should also do data persistence in case of redis restart

Depending on the system QPS may need a redis-cluster in the future



advantage: good performance, fast response
disadvantage: extra cost, extra learning cost

![a608b0fb443ae08822f5c85bddc636a7.png](https://i.mji.rip/2023/11/21/a608b0fb443ae08822f5c85bddc636a7.png)



