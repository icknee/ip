# Bird User Guide

 <pre>   .-.  
  /'v'\  
 (/   \)  
='="="===<  
   |_|
 </pre> 
Bird is a desktop app for managing tasks,
optimized for use via a Command Line Interface (CLI). If you can type fast, Bird can 
get your task management done faster than traditional GUI apps. The app also auto saves and loads 
via a txt file.  

- [Features](#Features)
  - [To Dos](#To-Dos)
  - [Deadlines](#Deadlines)
  - [Events](#Events)
  - [List](#List)
  - [Mark](#mark)
  - [Unmark](#unmark)
  - [Delete](#delete)
  - [Find](#find)
  - [Exit](#exit)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
- [Command Summary](#Command-Summary)



## Features

> **Notes about the command format:**  
> Words bounded in square brackets `[]` are parameters to be supplied by the user. You may write an integer
or word(s) for the parameter, depending on the command.  
> 
> e.g. in `todo [TASK_DESCRIPTION],` `TASK_DESCRIPTION` is a parameter which can be
used as `todo read a book`.  
e.g. in `mark [TASK_NUMBER]`, `TASK_NUMBER` is a parameter which can be used as  `mark 1`.


## Adding tasks

You can add 3 kinds of tasks:

- To dos
- Deadlines
- Events  
  

### To Dos
Add a task with only the task description.
  
Format: `todo [TASK_DESCRIPTION]`  

Example: `todo read a book`

The output should reflect the task added as well as
the updated task count as shown below.

```
task added!
  [T][ ] read a book
you now have 1 task in the list
```  

### Deadlines
Add a task with the task description and its deadline.

Format: `deadline [TASK_DESCRIPTION] /by [DEADLINE]`

Example: `deadline CS2113 lecture quiz /by this friday 12pm`

The output should reflect the task added as well as
the updated task count as shown below.

```
task added!
  [D][ ] CS2113 lecture quiz (by: this friday 12pm)
you now have 2 tasks in the list
```  

### Events
Add an event with the event description, when its from, and when it is till.

Format: `event [EVENT_DESCRIPTION] /from [TIME_ITS_FROM] /to [TIME_ITS_TILL]`

Example: `event CS2113 lecture /from Friday 4pm /to 6pm`

The output should reflect the event added as well as
the updated task count as shown below.

```
task added!
  [E][ ] CS2113 lecture (from: Friday 4pm to: 6pm)
you now have 3 tasks in the list
```
## List
View the lists of tasks.

Format: `list`
>Note: any string that is written after `list` will be ignored.  
> e.g. `list random string` will be treated as `list`

The output should reflect the tasks stored in the file.

```
1. [T][ ] read a book
2. [D][ ] CS2113 lecture quiz (by: this friday 12pm)
3. [E][ ] CS2113 lecture (from: Friday 4pm to: 6pm)
```

## Mark
Mark a task as done.

Format: `mark [TASK_NUMBER]`  
> Note: `[TASK_NUMBER]` should be a positive integer that is lesser or equal to the total
> number of tasks.

Example: `mark 2`

The output should show that the task has been marked as done

```
Nice! I've marked this task as done:
  [D][X] CS2113 lecture quiz (by: this friday 12pm)
```  

## Unmark
Mark a task as incomplete.

Format: `unmark [TASK_NUMBER]`
> Note: `[TASK_NUMBER]` should be a positive integer that is lesser or equal to the total
> number of tasks.

Example: `unmark 2`

The output should show that the task has been marked as incomplete

```
OK, I've marked this task as not done yet:
  [D][ ] CS2113 lecture quiz (by: this friday 12pm)
```  

## Delete
Delete a task.

Format: `delete [TASK_NUMBER]`
> Note: `[TASK_NUMBER]` should be a positive integer that is lesser or equal to the total
> number of tasks.

Example: `delete 1`

The output should show which task has been deleted as well as the updated task count.

```
task deleted!
	[T][ ] read a book
you now have 2 tasks in the list
```

## Find
Find a task using a keyword.

Format: `find [keyword]`
>Note: The keyword is case-sensitive

Example: `find CS2113`

The output should show which task has been deleted as well as the updated task count.

```
Here are the tasks containing CS2113:
	[D][ ] CS2113 lecture quiz (by: this friday 12pm)
	[E][ ] CS2113 lecture (from: Friday 4pm to: 6pm)
```

Additional example : `find quiz`  
```
Here are the tasks containing quiz:
	[D][ ] CS2113 lecture quiz (by: this friday 12pm)
```  
  
## Exit
Exit the program.

Format: `bye`  
>Note: any string that is written after `bye` will be ignored.  
> e.g. `bye random string` will be treated as `bye`  

The program should exit after printing out a farewell message.
```
Bye bye!
```

## Saving the data
Bird data are saved in the hard disk automatically after
any command that changes the data. There is no need to save manually.  

## Editing the data file
Bird data are saved automatically as a txt file `[JAR file location]/data/tasklist.txt.` 
Advanced users are welcome to update data directly by editing that data file. However, the formating
has to be followed in order to load the data properly. Changes made by editing the data file will only
be reflected when you restart the program.  
>Note: If corrupted file warning shows up, you have to edit the data file directly by changing the content to the correct format or clearing the file.
  
## Command Summary

| **Action**    | **Format, Examples**                                                                                                             |
|---------------|----------------------------------------------------------------------------------------------------------------------------------|
| **To Dos**    | `todo [TASK_DESCRIPTION]` <br/> e.g., `todo read a book`                                                                         |
| **Deadlines** | `deadline [TASK_DESCRIPTION] /by [DEADLINE]` <br/> e.g., `deadline CS2113 lecture quiz /by this friday 12pm`                     |
| **Events**    | `event [EVENT_DESCRIPTION] /from [TIME_ITS_FROM] /to [TIME_ITS_TILL]`<br/> e.g., `event CS2113 lecture /from Friday 4pm /to 6pm` |
| **List**      | `list`                                                                                                                           |
| **Mark**      | `mark [TASK_NUMBER]`<br/> e.g., `mark 2`                                                                                         |
| **Unmark**    | `mark [TASK_NUMBER]`<br/> e.g., `unmark 2`                                                                                       |
| **Delete**    | `delete [TASK_NUMBER]`<br/> e.g., `delete 1`                                                                                     |
| **Find**      | `find [KEYWORD]`<br/> e.g., `find CS2113`                                                                                        |
| **Exit**      | `bye`                                                                                                                            |