    
*** Java Server Faces Shop ***


Application
-----------
This is the component based approach (using plain JSF, in real life
probably use higher level component libraries, PrimeFaces, OpenFaces, ...)

GlassFish
--------
This JSF workshop uses CDI **only** (no JSF Managed Beans), 
Bean validation and runs on GlassFish 

MVC
----
View is Facelets, using JSF pages and Bootstrap (which is
not the way, JSP components will have their built in look, better
use PrimeFaces or other)
Overal layout in template.xhtml (Using #{request.contextPath} to get
application root). 
Pages have "backing beans" holding page data (using CDI beans)

Control is single CDI bean

Model is the shop model

Initialization
---------------
No special

Error handling
----------
Not much (none)

Auth-entication and-orization
-----------------------------
None. Possible to add standard JEE security using a filebased
realm (in GlassFish) see AuthBean and User classes

Clean URL
---------
No native support for clean URLs must use third party
libraries (PrettyFaces)

Pagination
---------
Handled by application (using AJAX). Normally components will handle
this by them selves (using PrimeFaces or similar)

Testing
------
None for web parts 

Resources
---------
All in folder resources

NOTE: How to link resources 
URLs are not resolved based on the file structure in the server side. 
URLs are resolved based on the real public web addresses of 
the resources in question. 

