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
                <a href="/horarios" class="">
                    <i class="material-icons">today</i>
                    <p>Horário Atual</p>
                </a>
            </div>
            <div class="col-auto">
                <a href="/recados" class="">
                    <i class="material-icons">sms</i>
                    <p>Recados</p>
                </a>
            </div>
            <div class="col-auto">
                <a href="/alunos" class="">
                    <i class="material-icons">people</i>
                    <p>Alunos</p>
                </a>
            </div>
            <c:if test="${usuarioSessao.perfil.admin}">
            	<div class="col-auto">
                <a href="/presenca" class="">
                    <i class="material-icons">timer</i>
                    <p>Presença</p>
                </a>
            </div>
            </c:if>
            <c:if test="${usuarioSessao.perfil.professor}">
            	<div class="col-auto">
                	<a href="/meusHorarios" class="">
                    	<i class="material-icons">timer</i>
                    	<p>Meus Horários</p>
                	</a>
            	</div>
            </c:if>
            
        </div>
    </div>