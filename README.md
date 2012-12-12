cs420siuc2012
=============

Our project for CS 420 in Fall 2012 at SIUC

Note: The server being used to send updates to all users is a private server and we be taken down on Monday, Dec 17th
The server code will be included and you can start the server yourself for testing after that point in time. 

Extract: in the directory you wish to host the project in do the following
  Linux: 
    Unzip <filename>
  Windows:
    Right click zip file and unzip in desired directory

Build:
    Open a terminal and cd to the folder with the project buildfile (build.xml) and run ant

Run
    In the directory you built the project in, run the command java -jar out/jar/bse.jar
    There should be no issues connecting from a computer on campus.  If you are outside of campus, you may need to contact 
    Jordan Martin to have your IP added to the database firewall.

Use
    Now that the project is running, you can login.  Please use username test1 or test2 with password SIUCS and then click 
    login.  Now you will be in the main program and can both list and view items for sale and that you wish to buy. You will
    notice 5 different tabs along the top for navigating to different things.
    
    Exit - All tabs have a button that can be pressed for exiting the program at anytime.
    
    WTB tab - Listing of items that are for sale. The buttons at the bottom will allow you to refresh the list, and view the 
              items and vendors in a more detailed manner. Simply click on an item from the list and press the "Items Details"
              or "Vendor Info" button to launch a popup window displaying more information about them. 
              
    My Items for Sale tab - This tab allows you to add, edit, or remove the items you have for sale. These items are the ones 
              that show up in the WTB tab.
              
              Note: Listing ID's need to be unique, If the item your trying to sell already exists you need to use it's Item ID 
                    and information. If it doesn't exist, use a unique item ID, and the item should be automatically added to the 
                    items table.
                    
              Editing - Click the item from the list you wish to edit, the textboxes will autofill with the information, change
                        whatever information you wish, and then hit the "Save Changes" button at the bottom.
              Removing Item - Click the Item you wish to remove and hit "Remove Selected Item"
              Add Item - Simple fill in all the boxes and hit the "Add New Item" button. 
              
    WTS tab - This tab allows users to browse items that people are currently looking to buy, but couldn't find it for sale in 
              the WTB listing.
              This tab works the same way as the WTB tab. 
              
    Items I Want tab - This tab allows users to add, edit, or remove items they wish to purchase. 
                       The tab uses the same set up as the "My Items for Sale" tab. 
                       
    My Vendors tab - The tab allows users to add, cancel, or edit vendor information. Users can manage multiple vendors here if 
                     would like to.
                     It works similiar to "My Items for Sale" and "Items I Want" tab. It has a listing of your vendors at the top 
                     and the appropriate buttons along the bottom. Simple click an item you wish to make changes to, make your 
                     changes, and hit the appropriate button. 
                     
              
    
    
    
