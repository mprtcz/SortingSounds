# SortingSounds
Java application which visualizes three different sorting algorithms: bubble sort, quicksort and array sort, optionally playing a different tone to every comparison it makes while sorting.

The application contains 4 controls and a button responsible for the start of visualization.
* Sorting Method ChoiceBox allowing to choose between different sorting algorithms to visualize
* Latency Slider which can be manipulated during runtime allowing the user to change the speed of the animation
* Sample size ChoiceBox which lets the user choose different sizes of an array to be sorted
* Play Sound CheckBox responsible for determining if the sound should be played during visualization

In the botton there is a label which informs the user about how many comparisons chosen algorithm had made during sorting.


**An example of the application running Bubble sort algorithm:**

![.gif, 1MB](https://media.giphy.com/media/3og0ICUlBTNtMIFA52/source.gif)


**Quicksort:**

![.gif, 921kB](https://media.giphy.com/media/3og0IuDILvMqW36xpK/source.gif)


**Dual-Pivot Quicksort implemented in Java api as Arrays.sort() method:**

![.gif, 1,05MB](https://media.giphy.com/media/3og0IUVLqcwg5N4PN6/source.gif)


---

To generate a jar file of this project using maven:
  1. after cloning the project, open terminal in the project's main directory and type:

  `mvn package`
  
  2. Next go to generated `target` directory:
  
  `cd target`
  
  3. To run generated jar file enter:
  
  `java -jar SortingSounds.jar`

