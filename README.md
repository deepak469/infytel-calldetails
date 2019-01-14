# infytel-calldetails

The problem with using the /refresh endpoint is that we have to manually fire a POST request to it. It is not automatically done when the configuration changes. Also we need to fire a request to /refresh of all the services which might get affected by the change in the property. That means we have to keep track of which property is used in which application. If we have 100 microservices using the property, we need to fire /refresh to all those microservices.

The solution is to use Spring-Cloud-Bus. This, along with Queuing service like RabbitMQ, will trigger refresh events on all dependent microservices. However, Spring-Cloud-Bus is beyond the scope of the curriculum.
