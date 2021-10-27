# comp350
## Aproach
This is an aplication for the front desk. 
One of the more annoying things when working with login applications is the race condition that happens when multiple people are registering at the same time. 
We avoid that.

## "Mid in Your Eye Spa" Application

The project is to create software for “Mud in Your Eye” (MiYE), a new, small hot spring health spa, located in a remote, scenic part of the US. 
The owner of MiYE wants new software to improvethespa’s operations, making it easier for customers to reserveservices and for employees to presentcustomers with their bills at checkout.
MiYE is a full-timeresort spa facility1, but being very small it has just one front desk clerkon duty at any time. 
Front desk clerksare local people, hired primarily for their friendliness, not their computer 1 A resort spa is one at which services are provided only to overnight guests, and the emphasis is on providing guests with a relaxing, vacation-like experience, centered on spa services. 
skills—onecan’t assume any sophistication in the use of computer software. 
The owner doesn’t have strong opinions about the nature of the technology to be used. 
Right now, theyhave some stand-alone systems to handle the hotel-like aspects (e.g., reserving rooms, checking into rooms, putting restaurant tabs on room accounts...).
It’s OK with the owner to have the new software focus on the health spa services and leave most of the existing systems separate. 
The only necessary connection is that the spa services software must be able to connect to the existing database of reservations and registrations.
A typical hot springs health spa provides a range of services, including mineral baths, massages, facials, and specialty treatments. 
MiYE offerssuchservices seven days a week, from 8 amto 8 pm. (Services are scheduled to begin before 8 pm; it is the latest end time.)
Each guest has a unique number, which is assigned at check-in (a separate process)and allows(among other things)the look-up of the beginning and end of the guest’s stay at the spa.  
To reserve or receive a service, the guest must use this number.  
The service can only be scheduled during the guest’s stay—no reservations for future stays are allowed. 
Guests are charged for every service they reserve. 
Charges are made at the time of reservations. 
Reservationsare necessary for all services. 
Payment of charges occurs at checkout.(Payment is a separate process, which also includes charges for rooms, food, and other things besides services.)
A guest may cancel a reservation for a service at no charge, if he/she does so within 10 minutes of making the reservation or at least 90 minutes before the reservation time; otherwise the guest is charged for the service, received or not. 


## SERVICES:


Guests can reserve mineral bath servicesfor 60 minutes or 90 minutes @ $2.50/minute. 
There are three kinds of massage services (Swedish, shiatsu, and deep tissue), which guests can schedule for 30 or 60 minutes @ $3.00/minute. 
(There are 2 kinds of facial services (normal and collagen), which guests can schedule for 30 or 60 minutes @ $2.00/minute. 
(There are 4 kindsof specialty treatment services (hot stone, sugar scrub, herbal body wrap, botanical mud wrap), which guests can schedule for 60 or 90 minutes @ $3.50/minute. 
(No guest may reserve overlapping services, although consecutive services are allowed.  
Only one guest at a time may reserve anyservice, except mineral bath services, which have no limit.
The software should make it clear to the front desk clerk what appointments are available at any particular day/time.  
It should only allow a reservation for a service to be made if: 
1) the guest has no overlapping reservation already booked, 
2) there is no other reservation for the service for that day/time, and 
3) the reservation would begin no earlier than 8 amand end no later than 8 pm.
The system should produce a bill that lists the spa services reserved, other than those canceled(if any), by the customer. 
It should calculate the amount charged for each service, as well as a total (pre-tax).
The software must be correct. Full testing is expected and should be explained during the presentation.
The software must be flexible enough to easily accommodate changes 

## Useful Link
+ [Link to Docker Image](https://www.docker.com/) <img src="img/docker.png" alt="alt text" width="50" height="50">
