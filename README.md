# Section 1
Introduction 
Hashtables are a critical part of many data structures, making it relatively easy to store, search and access information quickly with the help of detection probes. Two commonly used detectors are linear probe and quadratic probing, which are both used to create useful hashtables. This essay will discuss why these two detection probes make effective hash table creation possible, their strengths and weaknesses in terms of creating an efficient data structure, and what criteria should be considered when selecting one over the other for particular tasks. Ultimately, by examining the applications of linear probe and quadratic probing in hashtable construction this essay will present an objective analysis on how best to create a reliable and efficient hashtable using detection probes.

## How does the hashtable work?
The Hashtable class is an efficient data structure that uses a hash function to map keys to indices in an array, allowing for fast insertion, retrieval, and storage of key-value pairs. The class also employs a collision handling strategy, using a specified probe type such as linear, quadratic, or double hash to find an empty slot in the array when a collision occurs. The put method is used to store a key-value pair in the Hashtable, it first checks if the current load factor exceeds a maximum threshold, if so, it calls the resize method to increase the size of the Hashtable. The get method is used to retrieve the value associated with a given key, using the find method to search the array. The resize method increases the size of the Hashtable when necessary to ensure that the Hashtable stores and retrieves key-value pairs efficiently and effectively handles collisions, preventing the Hashtable from becoming too full. Overall these methods work together to optimize the performance of the Hashtable data structure.

## How does the put method work?
The put method is a crucial part of the Hashtable class, it is used to store key-value pairs in the Hashtable efficiently.The  method first checks and stores the hashed key in a variable and then creates a new variable with the type pair equal to the find pair method that i created to keep track of collisions. If there are no collisions in other words if foundPair is null findEmptyOrSameKey method is called to find an unoccupied position in the array to store the pair object containing the key and its value. If the key already exists, the value in the existing pair object is overwritten with the new value. Finally, if the load factor (the ratio of items stored to the size of the array) exceeds the maximum load factor (0.5), the resize method is called to increase the size of the array. This method ensures that the Hashtable stores key-value pairs efficiently, handles collisions effectively, and prevents the Hashtable from becoming too full by resizing when necessary.



## How does the get method work?

The get method I start by getting the hashed key by using the hash function. Then I use an if statement to check if the key is null using the haskey method. If the key is null it returns null. Then if these conditions are met it returns the value associated with the key we asked for using the find method which already returns a value associated with a given key.


## Managing the load factor
In hash table data structures, managing the load factor is important in order to maintain good performance and avoid hash collisions. The load factor is a measure of how full the hash table is and is calculated by  dividing the number of items stored in the hash table (itemCount) by the maximum number of items the hash table can store (max), and when it reaches a certain threshold, it may indicate that the table needs to be resized or that a different collision resolution strategy should be used. By keeping the load factor within an optimal range, the hash table can be optimized for time and space complexity, and ensure that lookups and insertions are as efficient as possible.


## Open addressing Vs Separate chaining
In the hashtable open addressing was used, open addressing is a technique to avoid collision. The basic goal of open addressing is to maintain all the data in one table, thus we must look for alternate hash table slots until we find one that works. This was achieved by the use of probes to avoid these collisions like linear pobs, quadratic probs, and double hashing. With linear probing, when we discover that a position for a hash key value is already occupied, we increment the hashed value by one,and then we check the resulting index. However, we start facing problems when the table is large enough primary clustering can occur. On the other hand this problem does not occur if you use a quadratic prob that searches through the matrix in a quadratic fashion instead of a linear fashion. Instead a milder form of the problem occurs called secondary clustering when two hashed values are similar. Double hashing is one of the most effective ways to deal with collisions as it uses a different hashing function to deal with collisions(Yadav, 2022). 

Separate chaining is another method used to resolve collisions in a hashtable. Like Open addressing we also have to keep track of the load factor but unlike open addressing we do not use probes to detect collisions, instead we correspond each spot in the hashtable with a linked list and in case of collisions we store these elements in the same linked list. However seeing that each spot in the hashtable is equal to a list we can see how it can consume a lot of storage (Jain, 2022). 



# Section 2

## Introduction 

Operating systems are critical components of computer technology, managing and mediating computer activity. Understanding the data structures and algorithms used in operating systems is key to recognizing how complex tasks are performed by computers. In this section I will focus on the application of some of the most commonly used data structures and algorithms, including lists, array, stack, queue, trees and various sorting algorithms. 
Lists 
Lists are an integral data structure in the design of operating systems. This was thoroughly explored in the book “ Fundamentals of data structures”, in which the author discusses the advantages of lists in comparison to other data structures such as arrays. Lists possess a number of benefits that make them an ideal choice for operating system data structures and algorithms. Firstly, they are more flexible than arrays as they can be implemented as dynamic structures that can grow and shrink in size. This allows them to more easily accommodate new data and to better adjust to changes in the operating system environment. Secondly, they facilitate efficient data manipulation, as they are easily searchable and can be modified in linear time. Finally, they provide a way to store data in an order that is meaningful and can be easily manipulated in a systematic way (Horowitz and Sahni).

## Array 

The use of arrays for optimizing operating system performance has been a long-standing research topic since the early days of computing.This is discussed in the research paper - The Cache Performance and Optimization of Blocked Algorithms. The authors explored the benefits of using arrays to improve memory management, scheduling, and resource allocation in operating systems. For instance, they discussed the advantages of using array-based data structures to store information about memory allocation, system calls, and process scheduling. They noted that arrays help reduce the time taken to access data, improve system performance, and reduce memory fragmentation. Furthermore, the authors discussed the use of arrays to reduce the amount of disk space used by an operating system, as well as their potential for increased scalability. Additionally, they highlighted the potential for array-based algorithms to reduce the amount of time it takes to carry out certain operations. (Lam et al.)

## Stack 


Stacks are one of the most fundamental data structures used in operating systems. According to the ACM journal stacks are characterized by their Last-In-First-Out (LIFO) structure and are used in a variety of scenarios, such as to store return addresses during procedure calls, to store local variables during function calls, to store items temporarily during the execution of an algorithm, and to implement recursion. In addition, stacks can be used to implement backtracking algorithms, depth-first search, and breadth-first search algorithms. Furthermore, stacks are also used in the implementation of certain sorting algorithms and the storage of data for dynamic programming techniques. As such, stacks are an essential component in the development of a variety of operating system data structures and algorithms (Shavitt).




## Queue 

In 1978, J Vuillemin wrote a paper published in the journal ACM that examined the role of queues in operating system data structures and algorithms. According to Vuillemin, queues are an essential part of operating system architecture. Their primary use is to store data in a structure that is easy to access and update. Queues simplify the process of accessing data by providing a linear data structure that is easy to manipulate. Additionally, queues enable the operating system to distribute data to multiple applications in an orderly manner. Vuillemin also points out that queues can be used for synchronization, as they can provide a mechanism for applications to communicate with each other. This is because a queue can be used to store information regarding the status of a process, allowing applications to access it and act accordingly. Furthermore, Vuillemin highlights the importance of queue scheduling algorithms, which are used to determine the order in which data is processed. These algorithms allow for efficient and effective data processing, as they ensure that the most important tasks are completed first (Vuillemin, 1978).


## Trees 

The use of trees in operating system design is a topic that has been discussed in the academic journal ACM in the article- The Linux B-Tree Filesystem. They propose the use of trees in operating system design as a way of providing an efficient and reliable model for memory management. Trees can be used to organize data, as well as providing an efficient model to access and store data. The authors also discuss how trees can be used to provide more efficient scheduling algorithms and memory allocation algorithms. In addition, they discuss how the use of trees can reduce the amount of time spent on disk reads and writes, thus improving overall system performance. Trees can also provide better transaction processing, as well as providing an efficient way to store and access data (Rodeh et al.).


## Muli sorting algorithms 
Y Lian et al recently (2022) conducted a study to examine AGV. In this study the author discusses the impact of various multi-sorting algorithms in operating system performance in the research paper. Their findings suggest that using multi-sorting algorithms can lead to a significant improvement in operating system performance. Specifically, they found that using multi-sorting algorithms instead of traditional single-sorting algorithms can reduce the time required to complete a task by up to 25%. Furthermore, they found that the use of multi-sorting algorithms also improved the accuracy of the sorting process, leading to more accurate results. This suggests that multi-sorting algorithms are beneficial for operating system performance, as they can both reduce the time required for a task, in this case creating a self-driving car. As well as, improve the accuracy of the sorting process. Additionally it was found that the use of multi-sorting algorithms could also reduce the memory consumption of a system, leading to more efficient use of resources. This further demonstrates the usefulness of multi-sorting algorithms for improving operating system performance. Overall, the findings of this study suggest that multi-sorting algorithms have the potential to significantly improve operating system performance (Yang et al.).

