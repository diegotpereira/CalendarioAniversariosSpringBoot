<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<table class="table table-striped">
		<caption>Seu calendário abaixo</caption>
		<thead>
			<tr>
				<th>Nome</th>
				<th>SobreNome</th>
				<th>Data de Nascimento</th>
				<th>Local de nascimento</th>
				<th>Data</th>
				<th>Notas</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.firstName}</td>
					<td>${todo.lastName}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${todo.targetDate}" /></td>
					<td>${todo.place}</td>
					<td>${todo.desc}</td>
					<td><a type="button" class="btn btn-primary"
						href="/update-todo?id=${todo.id}">Edit</a> <a type="button"
						class="btn btn-warning" href="/delete-todo?id=${todo.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a type="button" class="btn btn-success" href="/add-todo">Add</a>
	</div>
</div>
<%@ include file="common/footer.jspf"%>