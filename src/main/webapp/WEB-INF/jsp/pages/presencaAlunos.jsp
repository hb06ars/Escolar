<!-- HEADER -->
<jsp:include page="includes/header.jsp" />
<!-- HEADER -->
<!-- TAGS -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!-- TAGS -->
<!-- INICIO BODY -->

<style>
.dot {
  height: 25px;
  width: 25px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
}
</style>


<!-- start: page -->
<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Presença dos Alunos</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-default" style="overflow:auto">
									<thead>
										<tr>
											<th>Matrícula</th>
											<th>Nome</th>
											<th>Mês/Ano</th>
											<th>01</th>
											<th>02</th>
											<th>03</th>
											<th>04</th>
											<th>05</th>
											<th>06</th>
											<th>07</th>
											<th>08</th>
											<th>09</th>
											<th>10</th>
											<th>11</th>
											<th>12</th>
											<th>13</th>
											<th>14</th>
											<th>15</th>
											<th>16</th>
											<th>17</th>
											<th>18</th>
											<th>19</th>
											<th>20</th>
											<th>21</th>
											<th>22</th>
											<th>23</th>
											<th>24</th>
											<th>25</th>
											<th>26</th>
											<th>27</th>
											<th>28</th>
											<th>29</th>
											<th>30</th>
											<th>31</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${usuarios }" var="u">
										
										<tr class="gradeX">
											<td>${u.matricula}</td>
											<td>${u.nome}</td>
											<td>${mes}/${ano}</td>
											<td><span class="dot" style="background-color: red;"></span></td>
											<td><span class="dot" style="background-color: green;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
											<td><span class="dot" style="background-color: #bbb;"></span></td>
										</tr>
										
										</c:forEach>
									</tbody>
								</table>
							</div>
						</section>





<!-- end: page -->
	</section>
</div>







<!-- FIM BODY -->
</div>
<!-- FIM BODY -->
<!-- MAIN NO HEADER -->
</main>
<!-- HEADER -->
<jsp:include page="includes/footer.jsp" />
<!-- HEADER -->