# Neighborhood Cat Watch

## Function of application:

- The application is designed to keep track of an
*arbitrary* amount of roaming cats in the user's 
neighborhood. 
- For each new cat the user finds roaming in the 
neighborhood, they must add it to the list of cats
for that neighborhood and log how many days it has
been fed for 7 days. 7 days is the minimum 
amount of days before it is assumed the cat has
no owner.
- The amount of times the user feeds it during the
7 days will determine whether they should either
send it to the shelter for adoption or send it to
the vet if it is not fed enough.
- Once this decision has been made, the cat will
be removed from the list.

## Targeted users and inspiration:
This is a very niche application, designed for cat
lovers on the lookout in their own neighborhood. 
This assumes that the user desires to feed the cats
as much as they can afford too, and that they have
a game plan for the cats' future. It is especially
designed for users who cannot take in the cats 
themselves. This would be an extremely beneficial 
application for me, as there are countless roaming 
cats in my current neighborhood. As with any cat 
lover, I want the best for these cats, and 
hopefully have them taken care of by others via 
adoption or a vet. 

## User Stories

- As a user, I want to be able to add a cat to my 
neighborhood
- As a user, I want to be able to view the list
of cats in my neighborhood
- As a user, I want to be able to mark a cat as 
available for adoption or needing to see the vet
- As a user, I want to be able to remove a cat 
from my neighborhood
- As a user, I want to be able to see the
number of cats in my neighborhood and the number 
of cats that have been adopted or sent to the
vet
- As a user, I want to edit whether a cat has
been fed or during each day
- As a user, when I select the quit option from 
the application menu, I want to be reminded to
save the neighborhood and the properties of 
each cat in the list to file, while having the
option to do so or not.
- As a user, when I start the application,
I want to be given the option to load my 
neighborhood from file.

# Instructions for Grader

- You can generate the first required action related to the
user story of adding a cat to the neighborhood by entering the 
name of the cat in the text box, and then clicking "Add Cat".
- You can generate the second required action related to the 
user story of removing a cat from the neighborhood by selecting 
a cat on the list and clicking "Delete Cat".
- You can locate my visual component by starting up the
application, where the splash screen will appear. The image 
file is in the "data" folder.
- You can save the state of my application by clicking "Save".
- You can reload the state of my application by clicking "Load".

# Phase 4: Task 2

Printing event log on window close:

Sat Apr 06 15:44:14 PDT 2024
Added cat: Oreo

Sat Apr 06 15:44:18 PDT 2024
Added cat: Sam

Sat Apr 06 15:44:25 PDT 2024
Added cat: Kelly's Cat

Sat Apr 06 15:44:28 PDT 2024
Increased days fed for: Kelly's Cat

Sat Apr 06 15:44:28 PDT 2024
Increased days passed for: Kelly's Cat

Sat Apr 06 15:44:28 PDT 2024
Fed cat: Kelly's Cat

Sat Apr 06 15:44:33 PDT 2024
Increased days fed for: Oreo

Sat Apr 06 15:44:33 PDT 2024
Increased days passed for: Oreo

Sat Apr 06 15:44:33 PDT 2024
Fed cat: Oreo

Sat Apr 06 15:44:37 PDT 2024
Increased days fed for: Sam

Sat Apr 06 15:44:37 PDT 2024
Increased days passed for: Sam

Sat Apr 06 15:44:37 PDT 2024
Fed cat: Sam

Sat Apr 06 15:44:41 PDT 2024
Increased days fed for: Oreo

Sat Apr 06 15:44:41 PDT 2024
Increased days passed for: Oreo

Sat Apr 06 15:44:41 PDT 2024
Fed cat: Oreo

Sat Apr 06 15:44:45 PDT 2024
Increased days fed for: Oreo

Sat Apr 06 15:44:45 PDT 2024
Increased days passed for: Oreo

Sat Apr 06 15:44:45 PDT 2024
Fed cat: Oreo

Sat Apr 06 15:44:48 PDT 2024
Increased days fed for: Oreo

Sat Apr 06 15:44:48 PDT 2024
Increased days passed for: Oreo

Sat Apr 06 15:44:48 PDT 2024
Fed cat: Oreo

Sat Apr 06 15:44:51 PDT 2024
Increased days passed for: Oreo

Sat Apr 06 15:44:51 PDT 2024
Cat not fed: Oreo

Sat Apr 06 15:44:54 PDT 2024
Increased days fed for: Kelly's Cat

Sat Apr 06 15:44:54 PDT 2024
Increased days passed for: Kelly's Cat

Sat Apr 06 15:44:54 PDT 2024
Fed cat: Kelly's Cat

Sat Apr 06 15:44:56 PDT 2024
Increased days fed for: Kelly's Cat

Sat Apr 06 15:44:56 PDT 2024
Increased days passed for: Kelly's Cat

Sat Apr 06 15:44:56 PDT 2024
Fed cat: Kelly's Cat

Sat Apr 06 15:44:58 PDT 2024
Increased days fed for: Kelly's Cat

Sat Apr 06 15:44:58 PDT 2024
Increased days passed for: Kelly's Cat

Sat Apr 06 15:44:58 PDT 2024
Fed cat: Kelly's Cat

Sat Apr 06 15:45:01 PDT 2024
Increased days fed for: Kelly's Cat

Sat Apr 06 15:45:01 PDT 2024
Increased days passed for: Kelly's Cat

Sat Apr 06 15:45:01 PDT 2024
Fed cat: Kelly's Cat

Sat Apr 06 15:45:03 PDT 2024
Increased days fed for: Kelly's Cat

Sat Apr 06 15:45:03 PDT 2024
Increased days passed for: Kelly's Cat

Sat Apr 06 15:45:03 PDT 2024
Fed cat: Kelly's Cat

Sat Apr 06 15:45:05 PDT 2024
Increased days fed for: Kelly's Cat

Sat Apr 06 15:45:05 PDT 2024
Increased days passed for: Kelly's Cat

Sat Apr 06 15:45:05 PDT 2024
Fed cat: Kelly's Cat

Sat Apr 06 15:45:10 PDT 2024
Kelly's Cat has been released and set for shelter or vet

Sat Apr 06 15:45:12 PDT 2024
Removed cat: Kelly's Cat
 
# Phase 4: Task 3

If I could refactor my design, I will not have classes nested
in a class. Overall, it can increase coupling, especially
if you nest it too deeply. Not only will readability be
low, but nesting will make the class more complex and could
possibly interfere with the single responsibility purpose for
the specific class. To refactor this, I will have it so that
the nesting classes are actually their own class within the 
appropriate packages. This way, coupling will be reduced, the
code would be a lot more readable to other users, and 
the single responsibility principle will be maintained.