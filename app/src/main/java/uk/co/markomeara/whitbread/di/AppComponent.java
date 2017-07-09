package uk.co.markomeara.whitbread.di;

import javax.inject.Singleton;

import dagger.Component;
import uk.co.markomeara.whitbread.venuesearch.VenueSearchActivity;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(VenueSearchActivity activity);
}