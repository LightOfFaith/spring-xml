@Controller
@RequestMapping

@RequestMapping(method=GET)=@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
@PatchMapping


# Supported method argument types

------------------------------------------------------------

1.  **org.springframework.web.context.request.WebRequest**  or **org.springframework.web.context.request.NativeWebRequest** . Allows for generic request parameter access as well as request/session attribute access, without ties to the native Servlet/Portlet API.
2.  **java.util.Locale**  for the current request locale, determined by the most specific locale resolver available, in effect, the configured **LocaleResolver**  / **LocaleContextResolver**  in an MVC environment.
3.  **java.util.TimeZone**  (Java 6+) / **java.time.ZoneId**  (on Java 8) for the time zone associated with the current request, as determined by a **LocaleContextResolver** .
4.  **java.io.InputStream**  / **java.io.Reader**  for access to the request’s content. This value is the raw InputStream/Reader as exposed by the Servlet API.
5.  **java.io.OutputStream**  / **java.io.Writer**  for generating the response’s content. This value is the raw OutputStream/Writer as exposed by the Servlet API.
6.  **org.springframework.http.HttpMethod**  for the HTTP request method.
7.  **java.security.Principal**  containing the currently authenticated user.
8.  **@PathVariable**  annotated parameters for access to URI template variables. See the section called “URI Template Patterns”.
9.  **@MatrixVariable**  annotated parameters for access to name-value pairs located in URI path segments. See the section called “Matrix Variables”.
10.  **@RequestParam**  annotated parameters for access to specific Servlet request parameters. Parameter values are converted to the declared method argument type. See [the section called “Binding request parameters to method parameters with @RequestParam”](https://docs.spring.io/spring/docs/4.3.20.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-requestparam "title")  .
11.  **@RequestHeader** annotated parameters for access to specific Servlet request HTTP headers. Parameter values are converted to the declared method argument type. See [the section called “Mapping request header attributes with the @RequestHeader annotation”](https://docs.spring.io/spring/docs/4.3.20.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-requestheader "title")  .
12.  **@RequestBody**  annotated parameters for access to the HTTP request body. Parameter values are converted to the declared method argument type using **HttpMessageConverter** s. See [the section called “Mapping the request body with the @RequestBody annotation”](https://docs.spring.io/spring/docs/4.3.20.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-requestbody "title") .
13.  **@RequestPart** annotated parameters for access to the content of a "multipart/form-data" request part. See [Section 22.10.5, “Handling a file upload request from programmatic clients”](https://docs.spring.io/spring/docs/4.3.20.RELEASE/spring-framework-reference/htmlsingle/#mvc-multipart-forms-non-browsers "title")  and [Section 22.10, “Spring’s multipart (file upload) support”](https://docs.spring.io/spring/docs/4.3.20.RELEASE/spring-framework-reference/htmlsingle/#mvc-multipart "title") .
14.  **@SessionAttribute**  annotated parameters for access to existing, permanent session attributes (e.g. user authentication object) as opposed to model attributes temporarily stored in the session as part of a controller workflow via **@SessionAttributes** .
15.  **@RequestAttribute**  annotated parameters for access to request attributes.
16.  **HttpEntity<?>**  parameters for access to the Servlet request HTTP headers and contents. The request stream will be converted to the entity body using HttpMessageConverters. See the section called “Using HttpEntity”.
17.  **java.util.Map**  / **org.springframework.ui.Model**  / **org.springframework.ui.ModelMap**  for enriching the implicit model that is exposed to the web view.
18.  **org.springframework.web.servlet.mvc.support.RedirectAttributes**  to specify the exact set of attributes to use in case of a redirect and also to add flash attributes (attributes stored temporarily on the server-side to make them available to the request after the redirect). See [the section called “Passing Data To the Redirect Target” and Section 22.6, “Using flash attributes”](https://docs.spring.io/spring/docs/4.3.20.RELEASE/spring-framework-reference/htmlsingle/#mvc-redirecting-passing-data "title") .
19.  Command or form objects to bind request parameters to bean properties (via setters) or directly to fields, with customizable type conversion, depending on **@InitBinder**  methods and/or the HandlerAdapter configuration. See the **webBindingInitializer**  property on **RequestMappingHandlerAdapter** . Such command objects along with their validation results will be exposed as model attributes by default, using the command class name - e.g. model attribute "orderAddress" for a command object of type "some.package.OrderAddress". The **ModelAttribute**  annotation can be used on a method argument to customize the model attribute name used.
20.  **org.springframework.validation.Errors**  / **org.springframework.validation.BindingResult**  validation results for a preceding command or form object (the immediately preceding method argument).
21.  **org.springframework.web.bind.support.SessionStatus**  status handle for marking form processing as complete, which triggers the cleanup of session attributes that have been indicated by the **@SessionAttributes**  annotation at the handler type level.
22.  **org.springframework.web.util.UriComponentsBuilder**  a builder for preparing a URL relative to the current request’s host, port, scheme, context path, and the literal part of the servlet mapping.


# Supported method return types

------------------------------------------------------------

> The following are the supported return types:

1.  A **ModelAndView** object, with the model implicitly enriched with command objects and the results of @ModelAttribute annotated reference data accessor methods.
2.  A **Model** object, with the view name implicitly determined through a **RequestToViewNameTranslator** and the model implicitly enriched with command objects and the results of **@ModelAttribute**  annotated reference data accessor methods.
3.  A **Model**  object, with the view name implicitly determined through a **RequestToViewNameTranslator**  and the model implicitly enriched with command objects and the results of **@ModelAttribute**  annotated reference data accessor methods.
4.  A **Map**  object for exposing a model, with the view name implicitly determined through a **RequestToViewNameTranslator**  and the model implicitly enriched with command objects and the results of **@ModelAttribute**  annotated reference data accessor methods.

5.  A **View**  object, with the model implicitly determined through command objects and **@ModelAttribute**  annotated reference data accessor methods. The handler method may also programmatically enrich the model by declaring a **Model**  argument (see above).
6.  A **String**  value that is interpreted as the logical view name, with the model implicitly determined through command objects and **@ModelAttribute**  annotated reference data accessor methods. The handler method may also programmatically enrich the model by declaring a **Model**  argument (see above).
7.  **void**  if the method handles the response itself (by writing the response content directly, declaring an argument of type **ServletResponse**  / **HttpServletResponse**  for that purpose) or if the view name is supposed to be implicitly determined through a **RequestToViewNameTranslator**  (not declaring a response argument in the handler method signature).
8.  If the method is annotated with **@ResponseBody** , the return type is written to the response HTTP body. The return value will be converted to the declared method argument type using **HttpMessageConverter** s. See [the section called “Mapping the response body with the @ResponseBody annotation”](https://docs.spring.io/spring/docs/4.3.20.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-responsebody "title") .
9.  An **HttpEntity<?>**  or **ResponseEntity<?>**  object to provide access to the Servlet response HTTP headers and contents. The entity body will be converted to the response stream using **HttpMessageConverter** s. See [the section called “Using HttpEntity”](https://docs.spring.io/spring/docs/4.3.20.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-httpentity "title") .
10.  An **HttpHeaders**  object to return a response with no body.
11.  A **Callable<?>**  can be returned when the application wants to produce the return value asynchronously in a thread managed by Spring MVC.
12.  A **DeferredResult<?>**  can be returned when the application wants to produce the return value from a thread of its own choosing.
13.  A **ListenableFuture<?>**  or **CompletableFuture<?>** /**CompletionStage<?>**  can be returned when the application wants to produce the value from a thread pool submission.
14.  A **ResponseBodyEmitter**  can be returned to write multiple objects to the response asynchronously; also supported as the body within a **ResponseEntity** .
15.  An **SseEmitter**  can be returned to write Server-Sent Events to the response asynchronously; also supported as the body within a **ResponseEntity** .
16.  A **StreamingResponseBody**  can be returned to write to the response OutputStream asynchronously; also supported as the body within a **ResponseEntity** .
17.  Any other return type is considered to be a single model attribute to be exposed to the view, using the attribute name specified through **@ModelAttribute**  at the method level (or the default attribute name based on the return type class name). The model is implicitly enriched with command objects and the results of **@ModelAttribute**  annotated reference data accessor methods.


# Example

*   @PathVariable
@GetMapping("/owners/{ownerId}")
public String findOwner(@PathVariable("ownerId") String theOwner, Model model) {
    // implementation omitted
}
The URI Template " /owners/{ownerId}`" specifies the variable name `ownerId.
====等价于
@GetMapping("/owners/{ownerId}")
public String findOwner(@PathVariable("ownerId") String theOwner, Model model) {
    // implementation omitted
}

@GetMapping("/owners/{ownerId}/pets/{petId}")
public String findPet(@PathVariable String ownerId, @PathVariable String petId, Model model) {
    Owner owner = ownerService.findOwner(ownerId);
    Pet pet = owner.getPet(petId);
    model.addAttribute("pet", pet);
    return "displayPet";
}
URI Template Patterns with Regular Expressions
The @RequestMapping annotation supports the use of regular expressions in URI template variables. **The syntax is {varName:regex} where the first part defines the variable name and the second - the regular expression.**  For example:
@RequestMapping("/spring-web/{symbolicName:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]+}")
public void handle(@PathVariable String version, @PathVariable String extension) {
    // ...
}

@MatrixVariable annotated parameters for access to name-value pairs located in URI path segments.
// GET /owners/42;q=11/pets/21;q=22

@GetMapping("/owners/{ownerId}/pets/{petId}")
public void findPet(
        @MatrixVariable(name="q", pathVar="ownerId") int q1,
        @MatrixVariable(name="q", pathVar="petId") int q2) {

    // q1 == 11
    // q2 == 22

}

WebApplicationInitializer
AbstractAnnotationConfigDispatcherServletInitializer
Upon initialization of a DispatcherServlet, Spring MVC looks for a file named [servlet-name]-servlet.xml in the WEB-INF directory of your web application and creates the beans defined there, overriding the definitions of any beans defined with the same name in the global scope.