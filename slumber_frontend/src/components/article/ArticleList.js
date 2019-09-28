import React, { Component } from 'react';
import ArticleCard from './ArticleCard';

class ArticleList extends Component {
    render() {
        var articleList = this.props.articles ? this.props.articles.map((article) => {
            return <div key={article.id} style={{ padding: "10px" }}><ArticleCard {...this.props} article={article} /></div>
        }) : "";

        return (
            <div style={{ width: "600px" }}>
                {articleList.length > 0 ? (
                    <h3>Blogs</h3>
                    
                ) : ""}
                {articleList}
                
            </div>
        );
    }
}

export default ArticleList;