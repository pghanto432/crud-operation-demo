# Create Employee:
curl --location 'http://localhost:9090/employees' \
--header 'Content-Type: application/json' \
--data '{
"name":"Pradeep",
"age":40,
"salary":2000000.0,
"department":"IT"
}'

``` java
@PostMapping
public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
    Employee createdEmployee = employeeService.createEmployee(employee);
    return ResponseEntity.status(HttpStatus.CREATED)
    .body(createdEmployee);
}
```
# 201 Created:
For a CREATE operation in a Spring Boot microservice, 
the appropriate HTTP status code is typically 201 Created. 
This status code indicates that the request has been fulfilled and has resulted in one or more new resources being created.  Here is an example of how you can implement a POST method in a Spring Boot controller to return a 201 Created status code:

# GET All Employees:
curl --location 'http://localhost:9090/employees'

``` java
@GetMapping
public ResponseEntity<List<Employee>> getAllEmployees() {
    List<Employee> employees = employeeService.getAllEmployees();
    return ResponseEntity.ok(employees);
}
```
# 200 Ok:
For the getAllEmployees method, 
the appropriate HTTP status code is 200 OK, 
which indicates that the request has succeeded and 
the response contains the requested data.

# GET Employee By Id:
curl --location 'http://localhost:9090/employees/1'

```java
@GetMapping("/{id}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
    Optional<Employee> employee = employeeService.getEmployeeById(id);
    return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}
```
# 200 Ok:
+ For the getEmployeeById method, the appropriate HTTP status code is also 200 OK if the employee is found. 
+ If the employee is not found, the appropriate status code is 404 Not Found.


# Update Employee:
curl --location --request PUT 'http://localhost:9090/employees/1' \
--header 'Content-Type: application/json' \
--data '{
"name":"Pradeep",
"age":40,
"salary":4000000.0,
"department":"IT"
}'

``` java
@PutMapping("/{id}")
public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
    Optional<Employee> employee = employeeService.getEmployeeById(id);
    if (employee.isPresent()) {
        Employee updatedEmployee = employee.get();
        updatedEmployee.setName(employeeDetails.getName());
        updatedEmployee.setAge(employeeDetails.getAge());
        updatedEmployee.setSalary(employeeDetails.getSalary());
        updatedEmployee.setDepartment(employeeDetails.getDepartment());
        employeeService.updateEmployee(updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
    } else {
        return ResponseEntity.notFound().build();
    }
}
```

* 200 OK if the employee is successfully updated.
* 404 Not Found if the employee with the specified ID is not found.


# Delete Employee:
curl --location --request DELETE 'http://localhost:9090/employees/1'

# In a Spring Boot microservice, a DELETE operation typically returns one of the following HTTP status codes:

# 204 No Content: 
This is the most commonly used status code for successful delete operations. 
It indicates that the resource was deleted successfully, and there’s no additional content 
to return in the response body.

``` java
@DeleteMapping("/resource/{id}")
public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
resourceService.deleteById(id);
return ResponseEntity.noContent().build();
}
```

# 200 OK: Sometimes
200 OK can be used if additional information is returned in the response body. 
For example, if you want to confirm the deletion by sending back a message.

``` java
@DeleteMapping("/resource/{id}")
public ResponseEntity<String> deleteResource(@PathVariable Long id) {
    resourceService.deleteById(id);
    return ResponseEntity.ok("Resource deleted successfully.");
}
```

# 404 Not Found: 
If the resource to delete does not exist, returning 404 Not Found is appropriate, 
indicating that the resource with the specified ID wasn’t found.

``` java
@DeleteMapping("/resource/{id}")
public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        if (!resourceService.existsById(id)) {
        return ResponseEntity.notFound().build();
        }
    resourceService.deleteById(id);
    return ResponseEntity.noContent().build();
}
```

# Summary
* Use 204 No Content for a successful delete with no response body.
* Use 200 OK if additional confirmation or details are returned.
* Use 404 Not Found if the resource does not exist.







