<html>
<head>
  <title>Web Demo</title>
</head>
<body>
<p>Say <a href="<%= request.getContextPath()%>/rest/hello">Hello</a></p> 

<form method="post" action="<%= request.getContextPath()%>/rest/hello">  
  <h2>Name:</h2>
  <p><%= request.getAttribute("user") %></p>
  <input type="text" id="say-hello-text-input" name="say-hello-text-input" value='<%= request.getParameter("say-hello-text-input") %>'/>
  <input type="submit" id="say-hello-button" value="Say Hello" />
</form>
</body>
</html>
