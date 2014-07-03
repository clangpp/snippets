package com.clangpp.snippets;

/**
 * Snippet data structure.
 */
public class Snippet {
  /**
   * A simple builder for class Snippet.
   * NOTE: No field of built Snippet shall be null.
   * NOTE: Please keep sync with class Snippet fields.
   */
  public static class Builder {
    public static final String EMPTY_CONTENT = "";
    public static final String EMPTY_ID = "";
    
    private final Snippet snippet;

    public Builder(Snippet snippet) {
      this.snippet = new Snippet();
      setContent(snippet.getContent());
      setTimestamp(snippet.getTimestamp());
      setId(snippet.getId());
    }

    public Builder() {
      this(new Snippet());
      setTimestamp(System.currentTimeMillis());
    }

    public Builder setContent(String content) {
      if (content == null) {
        content = EMPTY_CONTENT;
      }
      snippet.content = content;
      return this;
    }

    public Builder setTimestamp(long timestamp) {
      snippet.timestamp = timestamp;
      return this;
    }

    public Builder setId(String id) {
      if (id == null) {
        id = EMPTY_ID;
      }
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

  public long getTimestamp() {
    return timestamp;
  }

  public String getId() {
    return id;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static Builder newBuilder(Snippet snippet) {
    return new Builder(snippet);
  }

}
