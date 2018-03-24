package demo.reducer

import demo.AppStore.HandlerFunction

private[demo] object Reducers {
  def getReducers: Seq[HandlerFunction] = Seq(//order counts!!
    new AjaxReducer(),
    new NavigationReducer(),
    new LoinFormReducer()
  )
}
