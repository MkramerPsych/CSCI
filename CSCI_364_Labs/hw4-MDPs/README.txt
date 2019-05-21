1. The dominant trend observed was that the higher the gamma value, the higher the average cumulative rewards were. The only deviation from our trend was that 0.1 had a higher avg reward than 0.3. This trend probably arose due to the advantageous nature of relying on future changes in fire intensity to keep fires from reaching the burned out stage.

2. The trend for avg fires put out rose steadily from a gamma of 0 to a gamma of 0.9, but actually gamma values of 0.5 and 0.7 had a slightly higher value than the gamma of 0.9. This may be due to overexpectation on the part of the agent, where the fires were put out with sufficient frequency as to not have as many fires to put out on the whole.

3. While there are obvious similarities between our line graphs (0 being the worst in terms of rewards and fires for a gamma value), the relatively linear positive trend of rewards is different than the inverted parabolic trend for fires. It may be that a very high gamma value like 0.9 has such efficiency in fighting fires that the average fires put out becomes lower than allowing for a lower gamma due to efficiency of movement and extinguishing.

4. We chose to implement a state class with values for all of the information in the csv file. We then created a validNS() function that used the state transition function to create a list of valid next states from the current state. We created an implementation of the reward function based off the x and y position of the agent and the intensity of the fire, and then utilized a main method to generate the list of states, test for valid next states, and then call a value iteration function to generate a Q value. Finally, we opened a policy.txt file and populated it with the selected state and action pair for every possible state (2304). The OOP route made a great deal of sense, as the static nature of the fires and not having to account for running out of suppressant made large parts of the assignment solvable by simply checking the values from the state object to verify conditions before taking actions.

5. The transition function proved to be the most difficult part of the assignment. Once the validNS function and the transition function were completed, the reward and value iteration functions were much easier to complete. The hardest thing to implement was the fire intensity changes, as accounting for all conditions that may arise in the problem required a lot of scenario testing.

6. We spent approximately 8 hours on this project over 2 days.

7. We affirm that we have adhered to the honor code on this assignment
Max Kramer
Declan Galleher