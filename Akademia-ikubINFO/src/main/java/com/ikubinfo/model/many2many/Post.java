package com.ikubinfo.model.many2many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity(name = "Post")
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"),
	inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags = new ArrayList<>();

	public void addTag(Tag tag) {
		tags.add(tag);
		tag.getPosts().add(this);
	}

	public Post() {
	}

	public void removeTag(Tag tag) {
		tags.remove(tag);
		tag.getPosts().remove(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Post))
			return false;
		return id != null && id.equals(((Post) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

}
