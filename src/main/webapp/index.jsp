<html>
<body>
<h2> Simple Library App </h2>
    <a>Choose what do you want to do.</a>
<br>
    <form method="get" action="${pageContext.request.contextPath}/people">
        <input type="submit" value="Go to 'People List' ->">
    </form>
    <form method="get" action="${pageContext.request.contextPath}/books">
        <input type="submit" value="Go to 'Book List' ->">
    </form>

</body>
</html>


