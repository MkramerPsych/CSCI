1.
Map: mapT.dat
Map read in successfully

total path cost with BFS 7.0
BFS goal reached:  (0.0, 0.0)
nodes expanded with BFS:  5
time spent with BFS:  0.0 seconds
BFS completed successfully

total path cost with UCS:  6.0
UCS goal reached:  (8.0, 5.0)
nodes expanded with UCS:  9
time spent with UCS:  0.0 seconds
UCS completed successfully

total path cost with A*:  6.0
A* goal reached:  (8.0, 5.0)
nodes expanded with A*:  4
time spent with A*:  0.0 seconds
A* completed successfully
//////////////////////////////////////////////////////////////////////
Map: mapO.dat
Map read in successfully

total path cost with BFS 0.08280000000000001
BFS goal reached:  (41.2904548, -82.2184917)
nodes expanded with BFS:  2101
time spent with BFS:  0.03125 seconds
BFS completed successfully

total path cost with UCS:  0.08240000000000001
UCS goal reached:  (41.2904548, -82.2184917)
nodes expanded with UCS:  982
time spent with UCS:  0.03125 seconds
UCS completed successfully

total path cost with A*:  0.08240000000000001
A* goal reached:  (41.2904548, -82.2184917)
nodes expanded with A*:  285
time spent with A*:  0.015625 seconds
A* completed successfully
////////////////////////////////////////////////////////////////////////
Map: mapC.dat
Map read in successfully

total path cost with BFS 0.11800000000000004
BFS goal reached:  (41.4843221, -81.7042646)
nodes expanded with BFS:  240651
time spent with BFS:  4.34375 seconds
BFS completed successfully

total path cost with UCS:  0.10949999999999999
UCS goal reached:  (41.483662, -81.730277)
nodes expanded with UCS:  10398
time spent with UCS:  0.15625 seconds
UCS completed successfully

total path cost with A*:  0.10949999999999999
A* goal reached:  (41.483662, -81.730277)
nodes expanded with A*:  4458
time spent with A*:  0.140625 seconds
A* completed successfully
////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////
2. I found a general trend amongst the three algorithms where BFS found a different
solution than the other two at significantly more nodes expanded and higher cost. UCS
and A* always arrived at the same solution and at the same cost, but A* always expanded
about half as many steps as UCS. My implementation of A* utilized a manhattan distance
heuristic.
////////////////////////////////////////////////////////////////////////
3. I do experience a decent amount of anxiety at the outset of larger assignments,
and I definitely had a difficult time starting the problem. Once I got BFS working,
I began to hit a stride and after meeting with you I began to see what I needed to
complete the implementations. I found UCS to be the most difficult to implement as
I had little experience with search algorithms in python and needed to understand that
I needed a tuple to store the costs alongside the states.
////////////////////////////////////////////////////////////////////////
4. I believe I spent about 4-5 hours in total over 2 days to complete the assignment
////////////////////////////////////////////////////////////////////////
I affirm that I adhered to the honor code on this assignment.
Max Kramer

NOTE: I was unable to calculate the path lengths, though I believe I was close with
how I set up the parent dictionaries. I also could not run mapL.dat as I believe there
may be a problem with my BFS that causes the time to complete to grow exponentially.




