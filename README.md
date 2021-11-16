# clienter
Own Simple Http client  implementation. 

Features:
 - declarative http calls
 - ignore ssl

# Example

Here is example api call: 

``` java
public class ApiCallExample {
    public static void main(String[] args) {
        SslHelper.ignoreSSl();
        var response = HttpClient.of("https://cat-fact.herokuapp.com/facts/")
                .method(Method.GET)
                .call();

        System.out.println(response);
    }
}

```
