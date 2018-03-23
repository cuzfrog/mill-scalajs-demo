package demo.component;

final class GreetingView extends ComponentP[GreetingView.Props] {
  override def render(): ReactRenders = {
    <.div()(props.value)
  }
}
