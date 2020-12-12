<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="footer">
        <div class="row no-gutters justify-content-center">
            <div class="col-auto">
                <a href="/home" <c:if test="${itemMenuSelecionado == 'home' || itemMenuSelecionado == 'pages/home'}">class="active" </c:if> >
                    <i class="material-icons">home</i>
                    <p>Home</p>
                </a>
            </div>
            <div class="col-auto">
                <a href="/horarios" <c:if test="${itemMenuSelecionado == 'pages/horarios'}">class="active" </c:if> >
                    <i class="material-icons">today</i>
                    <p>Horário Atual</p>
                </a>
            </div>
            <div class="col-auto">
                <a href="/recados" <c:if test="${itemMenuSelecionado == 'pages/recados'}">class="active" </c:if> >
                    <i class="material-icons">sms</i>
                    <p>Recados</p>
                </a>
            </div>
            <div class="col-auto">
                <a href="/alunos" <c:if test="${itemMenuSelecionado == 'pages/alunos'}">class="active" </c:if> >
                    <i class="material-icons">people</i>
                    <p>Alunos</p>
                </a>
            </div>
            <c:if test="${usuarioSessao.perfil.admin}">
            	<div class="col-auto">
                <a href="/presenca" <c:if test="${itemMenuSelecionado == 'pages/presenca'}">class="active" </c:if> >
                    <i class="material-icons">timer</i>
                    <p>Presença</p>
                </a>
            </div>
            </c:if>
            <c:if test="${usuarioSessao.perfil.professor}">
            	<div class="col-auto">
                	<a href="/meusHorarios" <c:if test="${itemMenuSelecionado == 'pages/meusHorarios'}">class="active" </c:if> >
                    	<i class="material-icons">timer</i>
                    	<p>Meus Horários</p>
                	</a>
            	</div>
            </c:if>
            
        </div>
    </div>