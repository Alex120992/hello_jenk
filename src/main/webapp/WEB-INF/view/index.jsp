<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Home Page</title>
    <meta charset="UTF-8">
</head>
<body>
<%--Помещаем вск формы в контейнер форм
создаем форму для ошибок--%>
<form:form action="sendmail" modelAttribute="personData" method="get">

    <form:input path="name" type="text" placeholder="Введите Ваше имя"/>
    <form:errors path="name"/>
    <br>
    <form:input path="mail" type="email" placeholder="Введите Вашу почту"/>
    <form:errors path="mail"/>

    <br>
    <form:textarea path="message" cols="10" maxlength="30" placeholder="Введите сообщение"/>
    <form:errors path="message"/>

    <br>
    <input type="reset" value="Сброс">
    <input type="submit"  value="Отправить">
</form:form>
</body>
</html>

