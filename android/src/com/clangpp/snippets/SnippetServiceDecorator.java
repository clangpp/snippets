package com.clangpp.snippets;

public abstract class SnippetServiceDecorator implements SnippetService {
  protected SnippetService snippetService;

  public SnippetServiceDecorator(SnippetService snippetService) {
    this.snippetService = snippetService;
  }
}
