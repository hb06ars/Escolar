<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="footer">
        <div class="row no-gutters justify-content-center">
            <div class="col-auto">
                <a href="/habibs/home" <c:if test="${itemMenu == '/pages/home' || itemMenu == 'home' }"> class="active" </c:if> >
                    <i class="material-icons">home</i>
                    <p>Home</p>
                </a>
            </div>
            <div class="col-auto">
                <a href="/habibs/ferias" <c:if test="${itemMenu == '/pages/ferias' }"> class="active" </c:if> >
                    <i class="material-icons">today</i>
                    <p>Escala</p>
                </a>
            </div>
            <div class="col-auto">
                <a href="/habibs/ctrlTime" <c:if test="${itemMenu == '/pages/ctrlTime' }"> class="active" </c:if> >
                    <i class="material-icons">today</i>
                    <p>Ctrl. Time</p>
                </a>
            </div>
            <div class="col-auto">
                <a href="/habibs/vendas" <c:if test="${itemMenu == '/pages/vendas' }"> class="active" </c:if>>
                    <i class="material-icons">shopping_cart</i>
                    <p>Vendas</p>
                </a>
            </div>
            <div class="col-auto">
                <a href="/habibs/telaTableau" <c:if test="${itemMenu == '/pages/tableau/telaTableau' }"> class="active" </c:if> >
                    <i class="material-icons">devices</i>
                    <p>Tableau</p>
                </a>
            </div>
        </div>
    </div>