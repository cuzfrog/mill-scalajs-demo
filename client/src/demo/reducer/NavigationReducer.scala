package demo.reducer

import demo.NavigationAction.Login
import demo.UserAction.LoginFormAction
import demo.{AppStore, NavigationModel, Page, RootModel}
import diode.{ActionHandler, Effect}

private final class NavigationReducer extends ActionHandler[RootModel, NavigationModel](AppStore.zoomTo(_.navigation)){
  override protected def handle = {
    case Login =>
      println("navigation Login!")
      updated(value.copy(currentPage = Page.ChatRoom), Effect.action(LoginFormAction.LoggedInClear))
  }
}
