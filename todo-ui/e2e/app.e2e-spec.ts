import { TodoUiPage } from './app.po';

describe('todo-ui App', function() {
  let page: TodoUiPage;

  beforeEach(() => {
    page = new TodoUiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
