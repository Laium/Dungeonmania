## Week 5

- List the tasks you completed this week here

## Week 6

- List the tasks you completed this week here

## Week 7

- Look at REPO start task 0
answered all of task 0 and to develop a basic understading on what the task at hand actually is, understood the battling logic and how they work and fucntion. 
figure differneve between dundgeonmaniacontrolelr and game 
purpose of callback system
importance of exact specification for test files 
how the tick system works 
https://nw-syd-gitlab.cseunsw.tech/COMP2511/25T1/groups/W17A_BEAGLE/assignment-ii/-/merge_requests/2

## Week 8

- Task 1 A B C 
https://nw-syd-gitlab.cseunsw.tech/COMP2511/25T1/groups/W17A_BEAGLE/assignment-ii/-/merge_requests/4
https://nw-syd-gitlab.cseunsw.tech/COMP2511/25T1/groups/W17A_BEAGLE/assignment-ii/-/merge_requests/6
A:
removed repeat code vioating DRY
used strategy solution to  aboid hardcosding and help future implements 
B:
state pattern fro swapping playter state if invis or invincble 
encapsulate the logic for the states 
C:
split the object between battle and non battle objects so things like wood dont giuve buffs
allows the future coders when impoement new items they can deicded which to use 
if they wanna add something like dirt they coiuld make it in the non battle obkjects 
unless the dirt gave powers


## Week 9

- Task 1 D E F
https://nw-syd-gitlab.cseunsw.tech/COMP2511/25T1/groups/W17A_BEAGLE/assignment-ii/-/merge_requests/7
https://nw-syd-gitlab.cseunsw.tech/COMP2511/25T1/groups/W17A_BEAGLE/assignment-ii/-/merge_requests/9

D: 
fixed feature envy in bomb 
moved logic to bomb class to deal with its own stuff rather than let switch deal with it 
E:
Goals implements the compsotie pattern
made own files for each goal to made not violet OCP
F:
1 refacvtor 
fixed the fucntions set as deprecated
swapped deprecated to their new ones

task 2B
        https://nw-syd-gitlab.cseunsw.tech/COMP2511/25T1/groups/W17A_BEAGLE/assignment-ii/-/merge_requests/16
        https://nw-syd-gitlab.cseunsw.tech/COMP2511/25T1/groups/W17A_BEAGLE/assignment-ii/-/merge_requests/11
        https://nw-syd-gitlab.cseunsw.tech/COMP2511/25T1/groups/W17A_BEAGLE/assignment-ii/-/merge_requests/15
        https://nw-syd-gitlab.cseunsw.tech/COMP2511/25T1/groups/W17A_BEAGLE/assignment-ii/-/merge_requests/22
    
BOSSES
assasin and hydra 

for assasin  have t o make a random variable to randomly decline bribes 
used randommath

for hydras had to make a new battle statistics class to change the way get health works so when hydras take damage they can potentially heal

All simialr test of zombies for hydras and mercs for assassin since they are meant to behave t he same way, but no spawner tests for hydra because assumed they dont have spawners.
General battle tests too.


