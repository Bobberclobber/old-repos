This is the company's own site. 
Conventional web design standards should be used when writing HTML, CSS and JS as well as when building the file structure
The design should be minimalistic and clear. 
Database should be handled using the SQLite3 plug-in for Python3.
The Flask plug-in for Python3 should eventually be used to tie together HTML, CSS and SQLite in a neat package.
JQuery is recommended as a plug-in for JavaScript
Store variables in css by typing the values and their main property/usage in a comment block at the top of the document

Graphical Profile:

Color Scheme:
	#E0E0E0 - Light Gray:
		Body's background color
	#00BF1A - Green:
		Current nav item's bottom border
		Hovering nav item's bottom border
		Hovering logo's text shadow
	#009915 - Dark Green:
		Clicked nav item's bottom border


Update log:

13/06-14
	Index page's HTML and CSS code basis created.
	Suggested colour scheme (the one currently used):
		#00BF1A - Medium Green: Currently used for the nav buttons' background colour
		#009915 - Dark Green: Currently used for clicked nav buttons' background colour
		#004009 - Very Dark Green: Currently used for the nav buttons' outlines when hovered over
		
14/06-14
	Nav buttons updated
	A new folder called 'font' has been added, this should be used to contain font files
	Logo created using the font Thurston (downloaded from DaFont)
	JQuery has been made available in index.html
	Functionality for the font Boris Black Bloxx added to style.css
	New colour added to the colour scheme:
		#E0E0E0 - Light Gray: Currently used for the site's background

27/06-14
	Nav buttons updated:
		Hovering now adds a green bottom border
		Clicking now changes the bottom border's color to dark green
		The current page's nav menu item has a constant green bottom border by applying the class '.active' using a short JQuery script
	Logo updated:
		Hovering now adds a green text shadow
	Color Scheme Updated:
		#004009 - Very Dark Green removed
		#00DF1A - Description changed to 'Green'. Now used for the nav buttons' bottom borders and the logo's hovering shadow
		#009915 - Now used for nav buttons' bottom borders when clicked

29/06-14
	A gallery page has been added, this is supposed to display out portfolio. 
	This should be achieved by placing a bunch of images in a folder which are then automatically displayed using javascript.
	Necessary files needed in order to work with Flask and Jinja2 alongside Python and html/css/js has been added
	These files are as follows:
		company_site.py - A python file which is used as the hub for accessing the different webpages as well as connecting them to a python database
		standard_template.html - A template html file which is used by other html files to add the content which should exist in all pages
	Furthermore, all html-files have been moved to the subdirectory 'templates', and the font, css and img directories have been move/created to/in the subdirectory 'static'
	From now on, all html-files should be placed in 'templates' and have functions defined for reaching them in company_site.py
	All recourse files (css, fonts, images etc.) should be placed in their respective subdirectories under 'static'
	For an overview of the basic capabilities of Flask and Jinja2, check out standard_template.html, index.html, gallery.html and company_site.py