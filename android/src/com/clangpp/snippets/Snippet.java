package com.clangpp.snippets;

/**
 * Snippet data structure.
 */
public class Snippet {
  /**
   * A simple builder for class Snippet. 
   * NOTE: Please keep sync with class Snippet fields.
   */
  public static class Builder {
    private final Snippet snippet;

    public Builder() {
      snippet = new Snippet();
      snippet.setTimestamp(System.currentTimeMillis());
      // TODO(clangpp): Consider proper default values for content and id.
    }
    
    public Builder(Snippet snippet) {
      this.snippet = new Snippet();
      this.snippet.setContent(snippet.getContent());
      this.snippet.setTimestamp(snippet.getTimestamp());
      this.snippet.setId(snippet.getId());
    }

    public Builder setContent(String content) {
      snippet.content = content;
      return this;
    }

    public Builder setTimestamp(long timestamp) {
      snippet.timestamp = timestamp;
      return this;
    }

    public Builder setId(String id) {
      snippet.id = id;
      return this;
    }

    public Snippet build() {
      return snippet;
    }
  }

  // Snippet content.
  private String content;

  // Snippet timestamp.
  private long timestamp;

  // Snippet ID.
  private String id;
  
  // NOTE: User should use Snippet.newBuilder() to generate Snippet instance.
  // NOTE: Private default constructor for Builder only.
  private Snippet() {
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public static Builder newBuilder() {
    return new Builder();
  }
  
  public static Builder newBuilder(Snippet snippet) {
    return new Builder(snippet);
  }

}
