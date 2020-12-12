<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="footer">
        <div class="row no-gutters justify-content-center">
            <div class="col-auto">
                <a href="/home" class="active">
                    <i class="material-icons">home</i>
                    <p>Home</p>
                </a>
            </div>
            <div class="col-auto">
                <a href="/ferias" class="">
                    <i class="material-icons">today</i>
                    <p>Horário Atual</p>
                </a>
            </div>
            <div class="col-auto">
                <a href="/vendas" class="">
                    <i class="material-icons">sms</i>
                    <p>Recados</p>
                </a>
            </div>
            <div class="col-auto">
                <a href="/tableau/telaTableau" class="">
                    <i class="material-icons">people</i>
                    <p>Alunos</p>
                </a>
            </div>
            <c:if test="${usuarioSessao.perfil.admin}">
            	<div class="col-auto">
                <a href="/tableau/comparativoFaturamento" class="">
                    <i class="material-icons">timer</i>
                    <p>Presença</p>
                </a>
            </div>
            </c:if>
            <c:if test="${usuarioSessao.perfil.professor}">
            	<div class="col-auto">
                	<a href="/tableau/comparativoFaturamento" class="">
                    	<i class="material-icons">timer</i>
                    	<p>Meus Horários</p>
                	</a>
            	</div>
            </c:if>
            
        </div>
    </div>