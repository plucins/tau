Scenario: A new task
Given a new task
When Task get own title - Call mom
Then user can show task Call mom name

Scenario: Get task by regex
Given init tasks list
When user wanna tasks with dog
Then user get all tasks with dog

Scenario: Remove task by regex
Given inits tasks list
When user wanna delete tasks with dog
Then user will remove all tasks contains dog
