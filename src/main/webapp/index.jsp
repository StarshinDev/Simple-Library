<html>
<body>
<h2> Simple Library App </h2>
<p margin-bottom="1"> Choose what do you want to do.</p>
    <form method="get" action="${pageContext.request.contextPath}/people">
        <input type="submit" value="Go to 'People List' ->"><p margin-bottom="1">
    </form>
    <form method="get" action="${pageContext.request.contextPath}/books">
        <input type="submit" value="Go to 'Book List' ->">
    </form>
</body>
</html>


