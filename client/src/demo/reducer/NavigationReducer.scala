package demo.reducer

import demo.Page
import demo.model.{AppStore, NavigationModel, RootModel}
import diode.{ActionHandler, Effect}

private[reducer] final class NavigationReducer extends ActionHandler[RootModel, NavigationModel](AppStore.zoomTo(_.navigation)) {
  import demo.action.NavigationAction._
  import demo.action.UserAction.LoginFormAction

  override protected def handle = {
    case Login =>
      println("navigation Login!")
      updated(value.copy(currentPage = Page.ChatRoom), Effect.action(LoginFormAction.LoggedInClear))
  }
}
