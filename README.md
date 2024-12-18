This code demonstrates an Apache Ignite Read-Through and Write-Through implementation with Spring Boot. It includes:

Ignite Configuration: Configures Ignite cache with readThrough and writeThrough enabled.
CommandLineRunner: Loads data from the database into the Ignite cache at startup.
Spring Data JPA Repository: Interfaces with the database.
CacheStore Implementation: Manages persistence logic for the cache.
User Entity: Represents a database table.
Feel free to customize the Entities and Repositories as per your table structure and application needs.
